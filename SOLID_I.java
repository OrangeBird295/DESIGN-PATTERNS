class Document {
}

interface Machine {
    void print(Document d);

    void fax(Document d) throws Exception;

    void scan(Document d) throws Exception;
}

class MultiFunctionPrinter implements Machine  { // OR implements Printer, Scanner, Fax

    @Override
    public void fax(Document d) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(Document d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scan(Document d) throws Exception {
        // TODO Auto-generated method stub
        
    }
   
}

class OldFashionPrinter implements Machine {

    @Override
    public void fax(Document d) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
        
    }

    @Override
    public void print(Document d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scan(Document d) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }
   
}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface Fax {
    void fax(Document d);
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {
    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document d) {
    }

    @Override
    public void scan(Document d) {
    }
}

/**
 * SOLID_I
 */
public class SOLID_I {

    
}