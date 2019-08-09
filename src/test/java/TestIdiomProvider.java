import org.junit.Assert;
import org.junit.Test;
import top.seraphjack.jielong.idiom.IdiomProvider;
import top.seraphjack.jielong.idiom.IdiomProviderFile;

public class TestIdiomProvider {
    @Test
    public void testIdiomProvider() {
        IdiomProvider provider = new IdiomProviderFile();
        Assert.assertTrue(provider.isValidIdiom("一个顶俩"));
        Assert.assertTrue(provider.isValidSequence("奋发图强", "强奸民意"));
        Assert.assertTrue(provider.isValidSequence("强奸民意", "一个顶俩"));
        Assert.assertFalse(provider.isValidSequence("大大小小", "奋发图强"));
        Assert.assertFalse(provider.isValidSequence("hjmm", "wtf?"));
        Assert.assertTrue(provider.isValidSequence("一心一意", "一心一意"));
    }
}
