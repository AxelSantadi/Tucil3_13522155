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
        Scanner input = new Scanner(System.in);
        String startWord, endWord;
        Set<String> wordList = readWordListFromFile("C:\\Users\\Axel Santadi\\Documents\\Cool_Yeah\\Tingkat_2\\Semester_4\\StiMa\\Tucil\\03\\Tucil3_13522155\\src\\Library\\words_alpha.txt");
        int choice;

        // Input startWord
        System.out.print("Masukkan startWord (Pastikan semua huruf besar): ");
        startWord = input.nextLine();
        while (!isValidWord(startWord) || !wordList.contains(startWord)) {
            System.out.println("Ups, katamu masih belum valid. Pastikan semua hurufnya adalah huruf besar, dan berbahasa inggris ya :3 Silakan coba lagi.");
            System.out.print("Masukkan startWord (Pastikan semua huruf besar): ");
            startWord = input.nextLine();
        } 

        // Input endWord
        System.out.print("Masukkan endWord (Pastikan semua huruf besar): ");
        endWord = input.nextLine();
        while (!isValidWord(endWord) || !wordList.contains(endWord) || startWord.length() != endWord.length()) {
            System.out.println("Ups, katamu masih belum valid. Pastikan semua hurufnya adalah huruf besar, panjangnya sama dengan kata awal, dan berbahasa inggris ya :3 Silakan coba lagi.");
            System.out.print("Masukkan endWord (Pastikan semua huruf besar): ");
            endWord = input.nextLine();
        }

        // Memasukkan input untuk algoritma yang dipilih
        System.out.println("Pilih algoritma yang ingin digunakan: ");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.print("Masukkan pilihan (1-3): ");

        long mulai = System.currentTimeMillis();

        // Input choice
        while (true) {
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang valid.");
                    System.out.print("Masukkan pilihan: ");
                }
            } else {
                System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang valid.");
                System.out.print("Masukkan pilihan: ");
                input.next(); // Clear buffer
            }
        }

        // Proses pemilihan algoritma
        switch (choice) {
            case 1:
                List<Object> ucsLadder = UCS.findWordLadderUCS(startWord, endWord, wordList);
                if (((List<?>) ucsLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(. Mungkin salah satu atau kedua kata bukan dalam bahasa inggris ya, who knows? ㄟ( ▔, ▔ )ㄏ");
                } else {
                    System.out.println("Hasil Dari Uniform Cost Search Algorithm: " + ucsLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + ucsLadder.get(1));
                }
                break;
            case 2:
                List<Object> greedyLadder = GBFS.findWordLadderGreedy(startWord, endWord, wordList);
                if (((List<?>) greedyLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(. Mungkin salah satu atau kedua kata bukan dalam bahasa inggris ya, who knows? ㄟ( ▔, ▔ )ㄏ");
                } else {
                    System.out.println("Hasil dari Greedy Best First Search algorithm: " + greedyLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + greedyLadder.get(1));
                }
                break;
            case 3:
                List<Object> aStarLadder = ABintang.findWordLadderAStar(startWord, endWord, wordList);
                if (((List<?>) aStarLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(. Mungkin salah satu atau kedua kata bukan dalam bahasa inggris ya, who knows? ㄟ( ▔, ▔ )ㄏ");
                } else {
                    System.out.println("Hasil dari A* algorithm: " + aStarLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + aStarLadder.get(1));
                }
                break;
        }
        long selesai = System.currentTimeMillis();
        System.out.println("Waktu eksekusi: " + (selesai - mulai) + " ms");

        // Close the scanner
        input.close();
    }

    // Method to check if a word contains only uppercase letters
    private static boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isUpperCase(word.charAt(i))) {
                System.out.println("Inputan tidak valid. Pastikan semua huruf besar.");
                return false;
            }
        }
        return true;
    }
}
