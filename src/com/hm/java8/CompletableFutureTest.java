package com.hm.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {

    public static void main(String[] args) {

        //futureWithoutReturnValue();
        //futureWithResult();
        //map();
        //flatMap();
        //combine();
        //acceptBoth();
        //whenComplete();
        //handle();
        //either();
        allOf();

    }

    private static void allOf() {
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(new Supplier<String>() {
                                 @Override
                                 public String get() {

                                     //String s=null;
                                     //s.substring(0,1);
                                     return "future1";
                                 }
                             }
                ).exceptionally(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                        return null;
                    }
                });
        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(new Supplier<String>() {
                                 @Override
                                 public String get() {

                                     return "future2";
                                 }
                             }
                );
        CompletableFuture<String> future3 = CompletableFuture
                .supplyAsync(new Supplier<String>() {
                                 @Override
                                 public String get() {

                                     return "future3";
                                 }
                             }
                );
        CompletableFuture.allOf(future1, future2, future3)
                .thenApply(new Function<Void, Object>() {
                    @Override
                    public Object apply(Void aVoid) {
                        return Stream.of(future1, future2, future3).map(new Function<CompletableFuture<String>, String>() {
                            @Override
                            public String apply(CompletableFuture<String> stringCompletableFuture) {
                               return stringCompletableFuture.join();
                            }
                        }).collect(Collectors.joining(" "));
                    }
                })
        .thenAccept(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });


    }


    private static void either() {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(new Supplier<String>() {
                                 @Override
                                 public String get() {
                                     try {
                                         Thread.sleep(random.nextInt(1000));
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }
                                     return "from future1";
                                 }
                             }
                );
        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(new Supplier<String>() {
                                 @Override
                                 public String get() {
                                     try {
                                         Thread.sleep(random.nextInt(1000));
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }
                                     return "from future2";
                                 }
                             }
                );
        CompletableFuture<Void> future = future1.acceptEither(future2, new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("future is " + s);
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "100";
            }
        }).handle(new BiFunction<String, Throwable, String>() {
            @Override
            public String apply(String s, Throwable throwable) {
                if (throwable != null) {
                    return "error";
                }
                return s;
            }
        });
        try {
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void whenComplete() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "100";
            }
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                if (throwable != null) {
                    System.out.println("error:" + throwable.getMessage());
                } else {
                    System.out.println("ok:" + s);
                }
            }
        });
    }

    private static void acceptBoth() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "100";
            }
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 100;
            }
        });
        CompletableFuture<Void> future3 = future1.thenAcceptBoth(future2, new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(Double.parseDouble(s + integer));
            }
        });
    }

    private static void combine() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "100";
            }
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 100;
            }
        });
        CompletableFuture<Double> future3 = future1.thenCombine(future2, new BiFunction<String, Integer, Double>() {
            @Override
            public Double apply(String s, Integer integer) {
                return Double.parseDouble(s + integer);
            }
        });
        try {
            System.out.println(future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void flatMap() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "world";
            }
        }).thenCompose(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String s) {
                return CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        return "hello " + s;
                    }
                });
            }
        });
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void map() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "world";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "hello " + s;
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void futureWithResult() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello world";
            }

        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果future已经执行完毕并且能返回结果，再调用complete不起作用。
        future.complete("world");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture return value");
    }

    private static void futureWithoutReturnValue() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }

}
