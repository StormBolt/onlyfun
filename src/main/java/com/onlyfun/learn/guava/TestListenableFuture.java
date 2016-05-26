package com.onlyfun.learn.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class TestListenableFuture {

	public static void main(String[] args) {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture explosion = service.submit(new Callable() {
			@Override
			public Object call() throws Exception {
				TimeUnit.SECONDS.sleep(10);
				return null;
			}
		});
		Futures.addCallback(explosion, new FutureCallback() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Object arg0) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> it is success");
			}
		});
		
		
	}

}
