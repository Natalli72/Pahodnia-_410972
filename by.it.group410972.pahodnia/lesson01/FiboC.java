package by.it.group410972.pahodnia.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m
 * время расчета должно быть не более 2 секунд
 */
public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d, %d)=%d \n\t time=%d \n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        if (n <= 1) return n % m;

        // Вычисляем период Пизано
        int pisanoPeriod = getPisanoPeriod(m);

        // Уменьшаем n с учетом периода Пизано
        n = n % pisanoPeriod;

        // Вычисляем n-е число Фибоначчи по модулю m
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    private int getPisanoPeriod(int m) {
        long prev = 0, curr = 1;
        for (int i = 0; i < m * m; i++) {
            long temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }
        return m;
    }
}