package stud.oiv.migration.beans;

public class ArtBook extends Book
{
    private String Genre;

    public ArtBook( String name, int pageCount, String author, String genre)
    {
        super( name, pageCount, author);
        Genre = genre;
    }

    public String getGenre()
    {
        return Genre;
    }

    public void setGenre(String genre)
    {
        Genre = genre;
    }

    @Override
    public String toString() {
        return String.format("id: %-20s  name: %-20s  page count: %-20d  author: %-20s  genre: %-20s",this.getId() ,this.getName() , this.getPageCount() , this.getAuthor() , Genre);
    }
}
