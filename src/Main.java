import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Set<String> readWordListFromFile(String filePath) {
        Set<String> wordList = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                wordList.add(word.trim().toUpperCase()); // Agar mudah, semua kata dibuat menjadi uppercase
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
    public static void main(String[] args) {
        String startWord;
        String endWord;
        Set<String> wordList = readWordListFromFile("C:\\Users\\Axel Santadi\\Documents\\Cool_Yeah\\Tingkat_2\\Semester_4\\StiMa\\Tucil\\03\\Tucil3_13522155\\src\\Library\\words_alpha.txt");

        // Memasukkan input untuk startWord dan endWord
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan startWord (Pastikan besar semua ya :D): ");
        startWord = input.nextLine();

        // checker apakah inputan startWord huruf besar semua
        for (int i = 0; i < startWord.length(); i++) {
            if (Character.isLowerCase(startWord.charAt(i))) {
                System.out.println("Inputan startWord tidak valid.");
                System.out.print("Masukkan startWord lagi (Pastikan besar semua ya :D): ");
                startWord = input.nextLine();
                i = 0;
            }
        }

        System.out.print("Masukkan endWord (Pastikan besar semua ya :D): ");
        endWord = input.nextLine();

        // checker apakah inputan endWord huruf besar semua
        for (int i = 0; i < endWord.length(); i++) {
            if (Character.isLowerCase(endWord.charAt(i))) {
                System.out.println("Inputan endWord tidak valid.");
                System.out.print("Masukkan endWord lagi (Pastikan besar semua ya :D): ");
                endWord = input.nextLine();
                i = 0;
            }
        }

        // Memasukkan input untuk algoritma yang dipilih
        System.out.println("Pilih algoritma yang ingin digunakan: ");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.print("Masukkan pilihan (Pastikan berupa angka ya :D): ");
        int choice = input.nextInt();

        while (choice < 1 || choice > 3) {
            System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang valid.");
            System.out.print("Masukkan pilihan: ");
            choice = input.nextInt();
        }

        
        if (choice == 1) {
            // Find word ladder using UCS algorithm
            List<String> ucsLadder = UCS.findWordLadderUCS(startWord, endWord, wordList);
            System.out.println("UCS Word Ladder: " + ucsLadder);
        } else if (choice == 2) {
            // Find word ladder using Greedy Best First Search algorithm
            List<String> greedyLadder = GBFS.findWordLadderGreedy(startWord, endWord, wordList);
            System.out.println("Greedy Best First Search Word Ladder: " + greedyLadder);
        } else {
            // Find word ladder using A* algorithm
            List<String> aStarLadder = ABintang.findWordLadderAStar(startWord, endWord, wordList);
            System.out.println("A* Word Ladder: " + aStarLadder);
        }

        input.close();
    }
}
