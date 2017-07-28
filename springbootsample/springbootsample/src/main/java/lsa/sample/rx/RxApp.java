package lsa.sample.rx;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RxApp {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                return "got";
            }
        }, exe);
        cf.thenAccept(System.out::println);
        exe.shutdown();

        final FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                int counter = 0;
                boolean finished = false;
                while (!finished) {
                    System.out.println("work in progress..." + counter);
                    Thread.sleep(1000);
                    counter += 1;
                    if (counter > 5) {
                        finished = true;
                        return "work done";
                    }
                }
                return "not finished";
            }
        });

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MILLISECONDS);
        System.out.println("future done? " + future.isDone());
        String done = task.get();
        scheduler.shutdown();
        System.out.println("future done? " + future.isDone());
        System.out.println(done);

        /*
        while (!future.isDone()) {
            System.out.println("not done");
            Thread.sleep(1);
        }
        */

        /*
        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable<String> source = Flowable.fromCallable(() -> {
            TimeUnit.SECONDS.sleep(2); //  imitate expensive computation
            return "Done";
        });
        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());
        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());
        showForeground.subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("hi");
        Thread.sleep(3000);

        Observable<String> infinite = Observable.interval(300, TimeUnit.MILLISECONDS).map(Object::toString);
        Observable<String> ticks = Observable.interval(1, TimeUnit.SECONDS).map(i -> "TICK " + (i + 1));
        Disposable subscription = infinite.mergeWith(ticks).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
        subscription.dispose();*/
    }
}
