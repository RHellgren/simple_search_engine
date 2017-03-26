import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class Searchwise_IO {
    public void run_program() {
        Searchwise_engine engine = new Searchwise_engine();
        Scanner in = new Scanner(System.in);
        int user_choice;
        boolean done = false;
        do {
            print_menu();
            user_choice = in.nextInt();

            switch (user_choice) {
                case 0:
                    done = true;
                    break;
                case 1:
                    System.out.println("Specify file path:");
                    String path_to_file = in.next();
                    engine.read_single_file(path_to_file);
                    break;
                case 2:
                    System.out.println("Specify folder path:");
                    String path_to_folder = in.next();
                    engine.read_multiple_files(path_to_folder);
                    break;
                case 3:
                    System.out.println("Input string (surrounded by \", e.g. \"Hello world!\"):");
                    String input_string = in.next();
                    engine.read_string_input(input_string);
                    break;
                case 4:
                    System.out.println("Specify search word:");
                    String search_word = in.next();
                    List<String> result = engine.search_for_string(search_word);
                    print_search_result(result);
                    break;
                default:
                    System.out.println("Unsupported input, please write a number between 0 and 4");
                    break;

            }
        } while (!done);
    }

    private void print_menu() {
        System.out.println("Welcome!");
        System.out.println("1 to read file.");
        System.out.println("2 to read files in folder.");
        System.out.println("3 to read string input.");
        System.out.println("4 to search for string.");
        System.out.println("0 to quit.");
    }

    private void print_search_result(List<String> result) {
    }
}
