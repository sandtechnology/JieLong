package top.seraphjack.jielong.idiom;

import com.google.gson.Gson;
import top.seraphjack.jielong.JieLong;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdiomProviderFile implements IdiomProvider {
    private Map<String, POJOIdiom> idiomMap = new HashMap<>();

    public IdiomProviderFile() {
        long startTime = System.currentTimeMillis();
        JieLong.logger.info("Loading idioms from jar file...");
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("assets/jielong/idiom.json");
        if (fis == null) {
            JieLong.logger.error("idiom.json is missing.");
            throw new RuntimeException("idiom.json is missing.");
        }
        Gson gson = new Gson();
        POJOIdiom[] idioms = gson.fromJson(new InputStreamReader(fis), POJOIdiom[].class);
        Arrays.stream(idioms).forEach(i -> idiomMap.put(i.word, i));
        JieLong.logger.info("Idioms load complete, took " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    @Override
    public boolean isValidIdiom(String idiom) {
        return idiomMap.containsKey(idiom);
    }

    @Override
    public boolean isValidSequence(String former, String idiom) {
        if (!(isValidIdiom(former) && isValidIdiom(idiom))) {
            return false;
        } else {
            return getLastPinyin(idiomMap.get(former).pinyin).equals(getFirstPinyin(idiomMap.get(idiom).pinyin));
        }
    }

    private String removeRedundantCharacters(String in) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) != 768) {
                sb.append(in.charAt(i));
            }
        }
        return sb.toString();
    }

    @Nullable
    private String getLastPinyin(String pinyin) {
        String ret;
        for (int i = pinyin.length() - 1; i > 0; i--) {
            if (pinyin.charAt(i) == ' ') {
                return removeRedundantCharacters(Normalizer.normalize(pinyin.substring(i + 1), Normalizer.Form.NFKD));
            }
        }
        return null;
    }

    @Nullable
    private String getFirstPinyin(String pinyin) {
        for (int i = 0; i < pinyin.length(); i++) {
            if (pinyin.charAt(i) == ' ') {
                return removeRedundantCharacters(Normalizer.normalize(pinyin.substring(0, i), Normalizer.Form.NFKD));
            }
        }
        return null;
    }
}
