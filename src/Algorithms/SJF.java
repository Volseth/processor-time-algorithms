package Algorithms;

import Interfaces.AcessAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;

import Program.Process;


public class SJF  implements AcessAlgorithm {
    private ArrayList<Process> targetList;
    public SJF(ArrayList<Process> targetList){
        this.targetList=targetList;
    }
    @SuppressWarnings("Duplicates")
    @Override
    public double calculateAverageAccessTime() {
        ArrayList<Process> executionList=new ArrayList<>();
        double time=0;
        double waitingTime=0;
        double totalWaitingTime=0;
        double size=targetList.size();
        targetList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getExecuteTime));
        for (int i=0;i<size;i++) {
            Process p=targetList.get(0);
            executionList.add(p);
            targetList.remove(p);
            Process workingProc=executionList.get(0);

            if(time<workingProc.getInputTime()){ time=workingProc.getInputTime(); }

            time += workingProc.getExecuteTime();
            waitingTime = time - (workingProc.getInputTime() + workingProc.getExecuteTime());
            if(p.getInputTime()<time){
                targetList.sort(Comparator.comparing(Process::getExecuteTime));
            }
            executionList.remove(workingProc);
            totalWaitingTime += waitingTime;

        }

        return totalWaitingTime/size;
    }
}