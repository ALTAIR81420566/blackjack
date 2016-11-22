package CardGame.Events;

/**
 * Created by vhurtin on 15.11.16.
 */
public class Command {
    private final Runnable runnable;

    Command(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}
