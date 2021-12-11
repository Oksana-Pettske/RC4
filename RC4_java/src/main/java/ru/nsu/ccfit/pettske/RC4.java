package ru.nsu.ccfit.pettske;

public class RC4 {
    //Массив, содержащий 2^n возможных перестановок слова
    static char[] S = new char[256];
    //Счетчики
    int x = 0;
    int y = 0;

    public RC4(char[] key) {
        KSA(key);
    }

    private static void Swap(int firstIndex, int secondIndex) {
        char temp = S[firstIndex];
        S[firstIndex] = S[secondIndex];
        S[secondIndex] = temp;
    }

    //Инициализация S-блока
    private void KSA(char[] key) {
        int keyLength = key.length;
        //заполнение массива
        for (int i = 0; i < 256; i++) {
            S[i] = (char) i;
        }
        //перемешивание массива в соответствии с ключом
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) % 256;
            Swap(i, j);
        }
    }

    //Генерация псевдослучайного слова
    private char PRGA() {
        x = (x + 1) % 256;
        y = (y + S[x]) % 256;
        Swap(x, y);
        return S[(S[x] + S[y]) % 256];
    }

    //Шифрование исходного сообщения
    public char[] Encode(char[] codeData, int size) {
        char[] data = new char[size];
        System.arraycopy(codeData, 0, data, 0, size);
        char[] cipher = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            //сложение исходного сообщения с псевдослучайным словом по модулю два
            cipher[i] = (char)(data[i] ^ PRGA());
        }
        return cipher;
    }

    //Декодирование
    //(повторное применение xor'а восстанавливает исходное значение,
    // поэтому для декодирования кодируем еще раз)
    public char[] Decode(char[] decodeData, int size) {
        return Encode(decodeData, size);
    }
}
