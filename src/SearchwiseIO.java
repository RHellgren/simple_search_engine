import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class SearchwiseIO {
    public void runProgram() throws IOException {
        System.out.println("Welcome!");
        SearchwiseEngine engine = new SearchwiseEngine();
        Scanner in = new Scanner(System.in);
        int userChoice;
        boolean done = false;
        do {
            printMenu();
            if (in.hasNextInt()) {
                userChoice = in.nextInt();
                switch (userChoice) {
                    case 0:
                        done = true;
                        break;
                    case 1:
                        System.out.println("Specify file name:");
                        String fileName = in.next();
                        engine.parseSingleFile(fileName);
                        break;
                    case 2:
                        System.out.println("Specify folder path:");
                        String pathToFolder = in.next();
                        engine.parseMultipleFiles(pathToFolder);
                        break;
                    case 3:
                        System.out.println("Specify search word:");
                        String searchWord = in.next();
                        List<String> result = engine.searchForString(searchWord.toLowerCase());
                        printSearchResult(result);
                        break;
                    default:
                        System.out.println("Not an option, please write a number between 0 and 3");
                        break;

                }
            } else {
                in.next();
                System.out.println("Unsupported input, please write a number between 0 and 3");
            }
        } while (!done);
    }

    private void printMenu() {
        System.out.println("1 to read file.");
        System.out.println("2 to read files in folder.");
        System.out.println("3 to search for string.");
        System.out.println("0 to quit.");
    }

    private void printSearchResult(List<String> resulting_documents) {
        resulting_documents.forEach(System.out::println);
    }
}
