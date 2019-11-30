package stud.oiv.migration.beans;
public class StudyBook extends Book
{
    private String Subject;

    public StudyBook(String name, int pageCount, String author, String subject)
    {
        super(name, pageCount, author);
        Subject = subject;
    }

    public String getSubject()
    {
        return Subject;
    }

    public void setSubject(String subject)
    {
        Subject = subject;
    }

    @Override
    public String toString() {
        return String.format("id: %-20s  name: %-20s  page count: %-20d  author: %-20s subject: %-20s", this.getId(), this.getName(), this.getPageCount(), this.getAuthor(), Subject);
    }
}
