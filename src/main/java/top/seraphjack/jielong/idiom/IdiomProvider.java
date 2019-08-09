package top.seraphjack.jielong.idiom;

public interface IdiomProvider {
    boolean isValidIdiom(String idiom);

    boolean isValidSequence(String former, String idiom);
}
