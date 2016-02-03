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
   // SearchRequest request= new SearchRequest(db,"567",0,db.size());  // prepare to search for "Ben"
		SearchRequest request;	
   // db.display();
   // System.out.println("\n");
   // db.searchByISBN(request);
	 	int temp = 0;
		for(int i=0; i<db.size(); i++){
				request = new SearchRequest(db, "567", temp, db.size());
				db.searchByISBN(request);
				temp = request.foundPos;
				if (request.foundPos >= 0) {
					temp++;
					System.out.println("Found matching title at position "+request.foundPos);
					System.out.println("Title: "+db.getBook(request.foundPos));
				}
				else
					i=db.size();
		}
  }
}

