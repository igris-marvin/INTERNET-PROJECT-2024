package sanienterprise.prototype.rest;

public class PrimeNumberManager { //implements something interface
    

    public boolean checkPrimeNumber(int number) {
        boolean flag = false;

        if(isPrimeNumber(number)) {
            flag = true;
        }

        return flag;
    }

    private boolean isPrimeNumber(int number) { //check if the number is a prime number
        boolean isPrime = false;

        //a prime number is a number that is divisible by 1 and itself
        for (int i = 2; i < number; i++) {
            
            if(number % i == 0) {
                isPrime = false;
            }
        }

        return isPrime;
    }
}
