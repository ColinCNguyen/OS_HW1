/**
 * Loads a list of books and then uses multiple threads to search all
 * fields show all matching fields.
 *
 * @author  Terry Sergeant
 * 
*/

import java.util.Scanner;
public class StudentDatabaseDriver
{
  public static void main(String [] args) throws Exception
  {
    BookDatabase db= new BookDatabase("books.txt");  // be sure this file exists!
		SearchRequest request;	
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter your search value: ");
		String findThis = kb.nextLine();

		//Continues looping until empty search input is given. Could condense
		//this but it seems to illustrate what is going on this way.
		while(!findThis.isEmpty()){	
			Thread searchISBN = new Thread(new threadSearch(db, 0, findThis));
			Thread searchTitle = new Thread(new threadSearch(db, 1, findThis));
			Thread searchAuthor = new Thread(new threadSearch(db, 2, findThis));
			Thread searchPage = new Thread(new threadSearch(db, 3, findThis));
			Thread searchYear = new Thread(new threadSearch(db,4, findThis));
			searchISBN.start();
			searchTitle.start();
			searchAuthor.start();
			searchPage.start();
			searchYear.start();

			searchISBN.join();
			searchTitle.join();
			searchAuthor.join();
			searchPage.join();
			searchYear.join();

			System.out.print("Enter your next search value: ");
			findThis = kb.nextLine();
		}
		kb.close();
  }
}

class threadSearch implements Runnable{

	BookDatabase db;
	int searchHow;
	String searchForThis;
	public threadSearch(BookDatabase aDb, int search, String find){
		db = aDb;
		searchHow = search;
		searchForThis = find;
	}

	/* Multiple ifs to check what type of search is happening.
	 * All loops are doing the same thing except the minor difference of changing the type of search
	 * being used.
	 * This could most likely be condensed, but I am unaware how I would do
	 * that currently.
	 */
	public void run(){
		if(searchHow==0){
			int isbnCount = 0;
			for(int i=0; i<db.size(); i++){
					SearchRequest requestIsbn = new SearchRequest(db, searchForThis, isbnCount, db.size());
					db.searchByISBN(requestIsbn);
					isbnCount = requestIsbn.foundPos;
					if (requestIsbn.foundPos >= 0) {
						isbnCount++;
						String formatThis = "Matched ISBN at position: " + requestIsbn.foundPos;
						System.out.printf("%-35s || Title: "+db.getBook(requestIsbn.foundPos)+"\n",formatThis);
					}
					else
						i=db.size();
			}
		}
		if(searchHow==1){
			int titleCount = 0;
			for(int i=0; i<db.size(); i++){
					SearchRequest requestTitle = new SearchRequest(db, searchForThis, titleCount, db.size());
					db.searchByTitle(requestTitle);
					titleCount = requestTitle.foundPos;
					if (requestTitle.foundPos >= 0) {
						titleCount++;
						String formatThis = "Matched title at position: " + requestTitle.foundPos;
						System.out.printf("%-35s || Title: "+db.getBook(requestTitle.foundPos)+"\n",formatThis);
					}
					else
						i=db.size();
			}
		}
		if(searchHow==2){
			int authorCount = 0;
			for(int i=0; i<db.size(); i++){
					SearchRequest requestAuthor = new SearchRequest(db, searchForThis, authorCount, db.size());
					db.searchByAuthor(requestAuthor);
					authorCount = requestAuthor.foundPos;
					if (requestAuthor.foundPos >= 0) {
						authorCount++;
						String formatThis = "Matched author at position: " + requestAuthor.foundPos;
						System.out.printf("%-35s || Title: "+db.getBook(requestAuthor.foundPos)+"\n",formatThis);
					}
					else
						i=db.size();
			}
		}
		if(searchHow==3){
			int pageCount = 0;
			for(int i=0; i<db.size(); i++){
					SearchRequest requestPage = new SearchRequest(db, searchForThis, pageCount, db.size());
					db.searchByNumPages(requestPage);
					pageCount = requestPage.foundPos;
					if (requestPage.foundPos >= 0) {
						pageCount++;
						String formatThis = "Matched pages at position: " + requestPage.foundPos;
						System.out.printf("%-35s || Title: "+db.getBook(requestPage.foundPos)+"\n",formatThis);
					}
					else
						i=db.size();
			}
		}
		if(searchHow==4){
			int yearCount = 0;
			for(int i=0; i<db.size(); i++){
					SearchRequest requestYear = new SearchRequest(db, searchForThis, yearCount, db.size());
					db.searchByYear(requestYear);
					yearCount = requestYear.foundPos;
					if (requestYear.foundPos >= 0) {
						yearCount++;
						String formatThis = "Matched year at position: " + requestYear.foundPos;
						System.out.printf("%-35s || Title: "+db.getBook(requestYear.foundPos)+"\n",formatThis);
					}
					else
						i=db.size();
			}
		}
	}
}
