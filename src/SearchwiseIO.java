import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Robin Hellgren on 2017-03-26.
 */
public class SearchwiseIO {
    public void runProgram() throws IOException {
        SearchwiseEngine engine = new SearchwiseEngine();
        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            printMenu();

            if (in.hasNextInt()) {
                int userChoice = in.nextInt();
                in.nextLine(); // Consumes the newline
                switch (userChoice) {
                    case 0:
                        System.out.println("Goodbye!");
                        done = true;
                        break;
                    case 1:
                        System.out.println("Specify file name:");
                        String fileName = in.nextLine();
                        engine.parseSingleFile(fileName);
                        break;
                    case 2:
                        System.out.println("Specify folder path:");
                        String pathToFolder = in.nextLine();
                        engine.parseMultipleFiles(pathToFolder);
                        break;
                    case 3:
                        System.out.println("Specify search word:");
                        String searchWord = in.nextLine();
                        if (engine.validSearchWord(searchWord)) {
                            List<String> result = engine.searchForString(searchWord.toLowerCase());
                            printSearchResult(result);
                        } else {
                            System.out.println("Invalid search term, only single term english words are searchable.");
                        }
                        break;
                    default:
                        System.out.println("Not an option, please write a number between 0 and 3");
                        break;
                }
            } else {
                in.next();
                System.out.println("Unsupported input, please write a number between 0 and 3");
            }
        }
    }

    private void printMenu() {
        System.out.println("1 to read file.");
        System.out.println("2 to read files in folder.");
        System.out.println("3 to search for string.");
        System.out.println("0 to quit.");
    }

    private void printSearchResult(List<String> resulting_documents) {
        if (resulting_documents.isEmpty())
            System.out.println("Could not find that word in any document listed.");
        else
            resulting_documents.forEach(System.out::println);
    }
}
