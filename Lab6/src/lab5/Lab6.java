//10.	Определить, содержит ли текст слова «Java» и «Hello», и определить их количество.

package lab5;
import java.io.*;
public class Lab6 {

 String data;
 String filename;
 String choice;
 String choice2;
 RandomAccessFile fio;
 BufferedReader in;
 
 public Lab6() throws UnsupportedEncodingException {
        this.in = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));//создание объекта класса BufferedReader с возможностью ввода данных на русском языке(кодировка "Cp1251")
    }
 
 public void runConsol() throws IOException {
        while (true) {
            printMenu();//вызов метода вывода меню на экран
            choice = in.readLine();//ввод выбранного пункта меню
            if (choice.compareTo("1") == 0) {//сравнение выбранного пользователем пункта меню
                textFromfile();//вызов метода чтения из файла
            } else if (choice.compareTo("2") == 0) {
                printRedactMenu();//вызов метода вывода возможных действий редактирования текста на экран
                choice2 = in.readLine();//ввод выбранного пункта
                if (choice2.compareTo("1") == 0) {
                    addStart();//вызов метода добавления текста в начало файла
                } else if (choice2.compareTo("2") == 0) {
                    addEnd();//вызов метода добавления текста в конец файла
                } else if (choice2.compareTo("3") == 0) {
                    addRandom();//вызов метода добавления текста в указаную позицию в файле
                }
            } else if (choice.compareTo("3") == 0) {
                actFile();//вызов метода чтения текста из файла и индивид зад
            }else if (choice.compareTo("4") == 0){
                writeBytes();
            } else if (choice.compareTo("5") == 0) {
                return;//выход из программы
            }
        }
    }

 public void printMenu() {//метод вывода меню на экран
        System.out.println("Введите ваш выбор:");
        System.out.println("1.Чтение текста из файла (исходного). ");
        System.out.println("2.Редактировать текст в файле");
        System.out.println("3.Выполнение действий над текстом.");
        System.out.println("4.Запись текста в файл.");
        System.out.println("5.Выход.");
    }

 public void textFromfile() throws IOException {//метод чтения файла
 System.out.println("Введите имя файла:");
        filename = in.readLine();//ввод имени файла, которое следует вводить учитывая расширение, например text.txt
        fio = new RandomAccessFile(new File(filename), "r");
        data = fio.readLine();//чтение информации из заданного файла
        fio.close();
        System.out.println("Информация из файла: " + data);//вывод информации из файла
 }
 
   public void printRedactMenu() {//метод вывода возможных действий редактирования текста на экран
        System.out.println("Выберите действие:");
        System.out.println("1 - добавление текста в начало файла");
        System.out.println("2 - добавление текста в конец файла");
        System.out.println("3 - добавление текста в произвольную позицию в файле");
    }

     public void addStart() throws IOException {//метод добавления текста в начало файла
        System.out.println("Введите имя файла:");
        filename = in.readLine();//ввод имени файла, которое следует вводить учитывая расширение, например text.txt
        fio = new RandomAccessFile(new File(filename), "rw");
        data = fio.readLine();//чтение информации из заданного файла
        System.out.println("Введите строку для добавления в начало:");
        String s;
        s = in.readLine();//ввод строки
        fio.seek(0);//переход в начало файла
        fio.writeBytes(s);//запись введенной строки
        fio.seek(s.length());//переход в конец записанной строки
        fio.writeBytes(data);//запись исходного текста после введенной строки
        fio.close();
        System.out.println("Cтрока записана в начало файла.");
    }

    public void addEnd() throws IOException {//метод добавления текста в конец файла
        System.out.println("Введите имя файла:");
        filename = in.readLine();//ввод имени файла, которое следует вводить учитывая расширение, например text.txt
        fio = new RandomAccessFile(new File(filename), "rw");
        data = fio.readLine();//чтение информации из заданного файла
        System.out.println("Введите строку для добавления в конец:");
        String s;
        s = in.readLine();//ввод строки
        fio.seek(fio.length());//переход в конец файла
        fio.writeBytes(s);//запись введенной строки в конец файла
        fio.close();
        System.out.println("Cтрока записана в конец файла.");
    }

    public void addRandom() throws IOException {//метод добавления текста в указаную позицию в файле
        System.out.println("Введите имя файла:");
        filename = in.readLine();//ввод имени файла, которое следует вводить учитывая расширение, например text.txt
        fio = new RandomAccessFile(new File(filename), "rw");
        System.out.println("Введите строку для добавления в указанную позицию в файле:");
        String s;
        s = in.readLine();//ввод строки
        System.out.println("Введите необходимую позицию в файле:");
        int n;
        n = Integer.parseInt(in.readLine());//ввод позиции
        fio.seek(n);//смещение на n позицию в файле
        data = fio.readLine();//чтение файла начиная с позиции n
        fio.seek(n);
        fio.writeBytes(s);//запись введенной строки с позиции n
        fio.writeBytes(data);//запись прочитанного с позиции n текста после введенной строки
        fio.close();
        System.out.println("Cтрока записана в файл.");
    }

 public void actFile() throws IOException {//метод для определения, содержит ли текст слова «Java» и «Hello», и определить их количество.
    System.out.println("Введите имя файла:");
    filename = in.readLine();//ввод имени файла, которое следует вводить учитывая расширение, например text.txt
    fio = new RandomAccessFile(new File(filename), "r");
    data = fio.readLine();//чтение информации из заданного файла
    fio.close();
    int i=data.indexOf("Hello");
    int k=data.indexOf("Java");
    int count1=0, count2=0;   
    System.out.println("Файл содержит слово 'Hello' - " + data.contains("Hello"));
    System.out.println("Файл содержит слово 'Java' - " + data.contains("Java"));
    while (i>=0){
        count1++;
        i=data.indexOf("Hello", i+1);
    }
    while (k>=0){
        count2++;
        k=data.indexOf("Java", k+1);
    }
    System.out.println("Количество слов 'Hello' - " + count1 +"\nКоличество слов 'Java' - " + count2  );
 }
 
 public void writeBytes() throws IOException { //Запись текста в файл
    //final void writeBytes (data)
    System.out.println("Файл записан" );
 }
 
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
    Lab6 n = new Lab6();//создание объекта класса Lab5
    n.runConsol();//вызов метода, выполняющего действия над файлом и строкой
    }    
}
