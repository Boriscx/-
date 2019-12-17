package com.cy.reference;

class ClassA {

    public void doShow(){
        System.out.println("show()");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize()");
        super.finalize();
    }
}
