package top.seraphjack.jielong.handler;

import static top.seraphjack.jielong.JieLong.idiomProvider;

public class JieLongHandler {
    private static JieLongHandler INSTANCE;
    private String currentIdiom;
    private int currentCount;

    private JieLongHandler() {

    }

    public static JieLongHandler instance() {
        if (INSTANCE == null) INSTANCE = new JieLongHandler();
        return INSTANCE;
    }

    public String getCurrentIdiom() {
        return currentIdiom;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public boolean startJieLong(String startIdiom) {
        if (idiomProvider.isValidIdiom(startIdiom)) {
            this.currentIdiom = startIdiom;
            this.currentCount = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean jieLong(String idiom) {
        if (idiomProvider.isValidSequence(currentIdiom, idiom)) {
            currentCount++;
            currentIdiom = idiom;
            return true;
        }
        return false;
    }
}
