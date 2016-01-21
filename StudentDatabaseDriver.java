/**
 * Loads a list of books and then uses multiple threads to search all
 * fields show all matching fields.
 *
 * @author  Terry Sergeant
 * 
*/

public class StudentDatabaseDriver
{
  public static void main(String [] args) throws Exception
  {
    BookDatabase db= new BookDatabase("books.txt");  // be sure this file exists!
    SearchRequest request= new SearchRequest(db,"Ben",0,db.size());  // prepare to search for "Ben"

    db.display();
    System.out.println("\n");

    db.searchByTitle(request);
    if (request.foundPos >= 0) {
      System.out.println("Found matching title at position "+request.foundPos);
      System.out.println("Title: "+db.getBook(request.foundPos));
    }
  }
}

