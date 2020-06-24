### Schedule Execution on Springboot StartUp
```text
  -Create A class name like [CallOnStartup]() and Implements ApplicationListener<ContextRefreshedEvent> and override onApplicationEvent.
  -Write All the function under onApplicationEvent method which you want to run on Application StartUp.
```

## Schedule Execution on Springboot StartUp as a thread 
```text
  -create an class file like [ThreadExecutionService]() and called function executeTask() which accept Runnable type input.
```

## Schedule Execution on Springboot StartUp Using Task Schedular
```text
   -Enable the Scheduling on main file as @EnableScheduling
   -Autowire TaskScheduler and call schedule which accept Runnbale type and time. 
```
