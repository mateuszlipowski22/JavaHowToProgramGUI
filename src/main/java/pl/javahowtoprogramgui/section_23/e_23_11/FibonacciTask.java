package pl.javahowtoprogramgui.section_23.e_23_11;

import javafx.concurrent.Task;

public class FibonacciTask extends Task<Long> {
    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Long call() throws Exception {
        updateMessage("Obliczam...");
        long result = fibonacci(n);
        updateMessage("Obliczenia zakończono.");
        return result;
    }

    public long fibonacci(int number){
        if(number==0 || number==1){
            return number;
        }else {
            return fibonacci(number-1) + fibonacci(number-2);
        }
    }
}
