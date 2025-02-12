import java.util.Scanner;

public class App {
    private static int[] genreWatchCount = new int[9];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
    }

    public static void mainMenu(Scanner scanner) {
        System.out.println("Welcome to KGGFlix!!!");
        System.out.println("Please Input what is your next action:");
        System.out.println("Input 1 to Login");
        System.out.println("Input 2 to Create an account");
        System.out.println("Input 3 to Exit");
        System.out.print("Input your choice: ");

        int action1 = scanner.nextInt();
        scanner.nextLine();

        switch (action1) {
            case 1:
                System.out.print("Input your Username: ");
                String username = scanner.next();

                System.out.print("Input your Password: ");
                String password = scanner.next();

                System.out.println("Login Successful! Welcome, " + username + "!");
                showMovieMenu(scanner);
                break;

            case 2:
                System.out.print("Input New Username: ");
                String newUsername = scanner.next();

                System.out.print("Input New Password: ");
                String newPassword = scanner.next();

                System.out.println("Account Created Successfully! Please login.");
                mainMenu(scanner);
                break;

            case 3:
                System.out.println("Exiting program...");
                return;

            default:
                System.out.println("Invalid option! Please select 1, 2, or 3.");
                mainMenu(scanner);
        }
    }

    public static void showMovieMenu(Scanner scanner) {
        System.out.println("\n==== KGGFlix Home ====");
        System.out.println("1. Browse Movies");
        System.out.println("2. View Recommendation");
        System.out.println("3. Log Out");
        System.out.print("Input your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                showGenreMenu(scanner);
                break;
        
            case 2:
                showRecommendations(scanner);
                break;

            case 3:
                System.out.println("Logging out...");
                mainMenu(scanner);
                break;
                
            default:
                System.out.println("Invalid choice. Try again.");
                showMovieMenu(scanner);
        }
    }

    public static void showRecommendations(Scanner scanner) {
        System.out.println("\n==== Recommended for You ====");
        
        int maxIndex = -1;
        int maxCount = 0;
        
        for (int i = 0; i < genreWatchCount.length; i++) {
            if (genreWatchCount[i] > maxCount) {
                maxCount = genreWatchCount[i];
                maxIndex = i;
            }
        }
    
        if (maxIndex == -1) {
            System.out.println("No recommendations yet. Watch some movies to get recommendations!");
        } else {
            System.out.println("Based on your watch history, we recommend more " + getGenreName(maxIndex) + " movies!");
            showMoviesByGenre(maxIndex + 1, getGenreName(maxIndex), scanner);
        }
    
        System.out.println("Press Enter to return to Home Menu.");
        scanner.nextLine();
        showMovieMenu(scanner);
    }
    
    public static String getGenreName(int index) {
        String[] genres = {"Action", "Comedy", "Documentaries", "Drama", "Fantasy", "Horror", "Romance", "Sci-Fi", "Thriller"};
        
        if (index >= 0 && index < genres.length) {
            return genres[index];
        }
        return "Unknown Genre";
    }
    
    public static void showGenreMenu(Scanner scanner) {
        System.out.println("==== Select Genre ====");
        String[] genres = {"Action", "Comedy", "Documentaries", "Drama", "Fantasy", "Horror", "Romance", "Sci-Fi", "Thriller"};

        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.println("10. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int genreChoice = scanner.nextInt();
        scanner.nextLine();

        if (genreChoice == 10) {
            System.out.println("Returning to main menu...");
            showMovieMenu(scanner);
        }

        if (genreChoice < 1 || genreChoice > genres.length) {
            System.out.println("Invalid choice. Try again.");
            showGenreMenu(scanner);
            return;
        }

        showMoviesByGenre(genreChoice, genres[genreChoice - 1], scanner);
    }

    public static void showMoviesByGenre(int genreChoice, String genreName, Scanner scanner) {
        String[][] movies = {
            {"Red Notice", "Extraction", "The Gray Man", "6 Underground", "Spenser Confidential"},
            {"Murder Mystery", "The Wrong Missy", "The Do-Over", "The Ridiculous 6", "Hubie Halloween"},
            {"Tiger King", "Making a Murderer", "The Social Dilemma", "Our Planet", "13th"},
            {"Bird Box", "The Irishman", "Marriage Story", "The Trial of the Chicago 7", "Pieces of a Woman"},
            {"The Witcher: Nightmare of the Wolf", "Bright", "The Christmas Chronicles", "Jingle Jangle", "The Willoughbys"},
            {"Bird Box", "Army of the Dead", "The Platform", "Fear Street Part One", "The Silence"},
            {"The Kissing Booth", "To All the Boys I've Loved Before", "The Perfect Date", "The Princess Switch", "A Christmas Prince"},
            {"The Midnight Sky", "I Am Mother", "Project Power", "The Platform", "Extinction"},
            {"Red Notice", "The Guilty", "The Woman in the Window", "The Devil All the Time", "The Old Guard"}
        };

        System.out.println("==== " + genreName + " Movies ====");
        for (int i = 0; i < movies[genreChoice - 1].length; i++) {
            System.out.println((i + 1) + ". " + movies[genreChoice - 1][i]);
        }
        System.out.println("6. Return to Genre Menu");

        System.out.print("Select a movie to watch: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine();

        if (movieChoice == 6) {
            showGenreMenu(scanner);
        } else if (movieChoice >= 1 && movieChoice <= 5) {
            playMovie(scanner, movies[genreChoice - 1][movieChoice - 1],genreChoice - 1);
        } else {
            System.out.println("Invalid choice. Try again.");
            showMoviesByGenre(genreChoice, genreName, scanner);
        }
    }

    public static void playMovie(Scanner scanner, String movieName, int genreIndex) {
        genreWatchCount[genreIndex]++; 
    
        System.out.println("Now Playing: " + movieName);
        System.out.println("1. Pause Movie");
        System.out.println("2. Exit Movie");
        System.out.print("Select an option: ");
    
        int action = scanner.nextInt();
        scanner.nextLine();
    
        switch (action) {
            case 1:
                System.out.println("Movie Paused. Press Enter to resume...");
                scanner.nextLine();
                playMovie(scanner, movieName, genreIndex);
                break;
            case 2:
                System.out.println("Exiting movie...");
                showGenreMenu(scanner);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                playMovie(scanner, movieName, genreIndex);
        }
    }
}    
