public class Word implements Comparable<Word> {
    public int freq;
    public String word;

    public Word(String w){
        this.freq = 1;
        this.word = w;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Word)) {
            return false;
        }
        Word w = (Word)o;
        return this.word.equals(w.word);
    }

    @Override
    public int compareTo(Word w){
        if(w.equals(this)){
            return 0;
        }
        if(this.freq == w.freq) {
            return this.word.compareTo(w.word);
        }
        return Integer.compare(w.freq, this.freq);
    }

}
