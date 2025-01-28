package org.junitpractice;

public class ValidateBet {
    private int betamount;


    public ValidateBet(int betamount) {
        this.betamount = betamount;
    }

    public int getBetamount() {
        return betamount;
    }

    public void setBetamount(int betamount) {
        this.betamount = betamount;
    }

    public Boolean validate(int accountBalance){
        return this.betamount<= accountBalance;
    }
}
