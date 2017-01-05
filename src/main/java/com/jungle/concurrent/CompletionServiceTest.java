package com.jungle.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceTest {
	
	public static void main(String[] args){
		new CompletionServiceTest().test();
        //new CallableTest().run();
	}

    class CalcResult {
         long result ;

         CalcResult(long l){
             result = l;
         }
    }

    class CallableTask implements Callable<CalcResult> {
        String taskName ;
        long  input1 ;
        int input2 ;

        CallableTask(String name , long v1 , int v2 ){
            taskName = name;
            input1 = v1;
            input2 = v2 ;
        }

        public CalcResult call() throws Exception {
            System.out.println(" Task " + taskName + " Started -----");
            final int multiNum = 10000;
            for(int i=0;i<input2*multiNum ;i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(" Task " + taskName + " Interrupted !! ");
                    e.printStackTrace();
                }
                input1 += i;
            }
            System.out.println(" Task " + taskName + " Completed @@@@@@");
            return new CalcResult(input1) ;
        }

    }

    public void test(){
        ExecutorService taskExecutor = Executors.newFixedThreadPool(10);
            CompletionService<CalcResult> taskCompletionService =
               new ExecutorCompletionService<CalcResult>(taskExecutor);

        int submittedTasks = 5;
        for(int i=0;i< submittedTasks;i++){
            taskCompletionService.submit(new CallableTask(
                    String.valueOf(i), 
                    (i * 10), 
                    ((i * 10) + 10  )
                    ));
           System.out.println("Task " + String.valueOf(i) + "subitted");
        }
        for(int tasksHandled=0;tasksHandled<submittedTasks;tasksHandled++){
            try {
                System.out.println("trying to take from Completion service");
                Future<CalcResult> result = taskCompletionService.take();
                System.out.println("result for a task availble in queue.Trying to get()"  );
                // above call blocks till at least one task is completed and results availble for it
                // but we dont have to worry which one

                // process the result here by doing result.get()
                CalcResult l = result.get();
                System.out.println("Task " + String.valueOf(tasksHandled) + " Completed - results obtained : " + String.valueOf(l.result));

            } catch (InterruptedException e) {
                // Something went wrong with a task submitted
                System.out.println("Error Interrupted exception");
                e.printStackTrace();
            } catch (ExecutionException e) {
                // Something went wrong with the result
                e.printStackTrace();
                System.out.println("Error get() threw exception");
            }
        }
        taskExecutor.shutdown();
    }
}

class CallableTest{
	
	class Work implements Callable<Integer>{
		private int _inc = 0;

		public Work(int inc){
			_inc = inc;
		}

        //@Override
		public Integer call(){
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                log("sleep error");
            }

			return _inc;
		}
	}
	
	public void run(){
        long startT = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
		Work work1 = new Work(1);
		Work work2 = new Work(2);
		Work work3 = new Work(3);
        List<Future<Integer>> fList = new ArrayList<Future<Integer>>();
        fList.add(executorService.submit(work1));
        fList.add(executorService.submit(work2));
        fList.add(executorService.submit(work3));
        Integer totalResult = 0;
        for(Future<Integer> future : fList) {
            try{
                log("try to get task"+future.toString());
                totalResult += future.get();
            }catch (Exception e){

            }
        }
        long endT = System.currentTimeMillis();
        log("total result:"+totalResult);
        log("take time:" + (endT - startT));
        executorService.shutdown();

	}

    private void log(String log){
        System.out.println(log);
    }
	
}