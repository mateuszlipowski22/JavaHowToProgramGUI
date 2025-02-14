package pl.javahowtoprogramgui.section_23.e_23_11_2;

import javafx.concurrent.Task;

import java.util.Arrays;

public class PrimeCalculatorTask extends Task<Integer> {
    private final boolean[] primes;

    public PrimeCalculatorTask(int max) {
        this.primes = new boolean[max];
        Arrays.fill(primes,true);
    }

    @Override
    protected Integer call() throws Exception {
        int count = 0;

        for (int i=2;i<primes.length;i++){
            if(isCancelled()){
                updateMessage("Anulowano");
                return 0;
            }else {
                try{
                    Thread.sleep(10);
                }catch (InterruptedException ex){
                    updateMessage("Przerwano");
                    return 0;
                }
                updateProgress(i+1,primes.length);

                if(primes[i]){
                    ++count;
                    updateMessage(String.format("Znaleziono %d liczb pierwszych", count));
                    updateValue(i);

                    for(int j = i+i; j <primes.length; j+=i){
                        primes[j] = false;
                    }
                }
            }
        }
        return 0;
    }
}
