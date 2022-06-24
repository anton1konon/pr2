public class Worker extends Thread {

    private int id;
    private final Data data;

    public Worker(int id, Data d) {
        this.id = id;
        data = d;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            synchronized (data) {
                try {
                    while (id != data.getState()) {
                        data.wait();
                    }
                    switch (id) {
                        case 1 -> data.Tic();
                        case 2 -> data.Tak();
                        case 3 -> data.Toy();
                    }
                    data.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
