package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that register and keeps the tasks
 */
public class ListTasks implements Serializable {
    /**
     * The list of tasks
     */
    private List<Task> m_lstTasks;

    /**
     * Initializes the list
     */
    public ListTasks() {
        this.m_lstTasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks
     * @return the list of tasks
     */
    public List<Task> getListTask() {
        return m_lstTasks;
    }

    /**
     * Cretes a new task
     * @param taskID the ID of the task
     * @param taskAssignDuration the duration of the task
     * @param taskCostPerHour the cost per hour of the task
     * @param taskCategory the category of the task
     * @param taskDescripton the description of the task
     * @return the task created
     */
    public Task newTask(String taskID, int taskAssignDuration, Double taskCostPerHour, String taskCategory, String taskDescripton){
        return new Task (taskID,taskAssignDuration,taskCostPerHour,taskCategory,taskDescripton);
    }

    /**
     * Returns the tasks that wasn't already transacted
     * @return the tasks that wasn't already transacted
     */
    public List<Task> getListTaskWithNoTransaction () {
        List<Task> listTaskWithNoTransactions = new ArrayList<>();
        for(Task t : getListTask()){
         if(!t.getTaskHasTransaction()){
             listTaskWithNoTransactions.add(t);
         }

        }
        return listTaskWithNoTransactions;
    }

    /**
     * Register a new task in the list
     * @param task the task to register
     * @return true if the task was registered, false if not
     */
    public boolean registerTask(Task task) {
        if(validate(task)){
            return m_lstTasks.add(task);
        } else {
            return false;
        }
    }

    /**
     * Validates the task
     * @param task the task to validate
     * @return true if there is no task registered with the same ID, false if not
     */
    public boolean validate(Task task) {
        for (Task t : m_lstTasks){
            if(t.getTaskID().equals(task.getTaskID())){
                if(!t.equals(task)){
                    throw new IllegalArgumentException("Task Already registered on the Platform !");
                }else{
                   return false;
                }
            }
        }
        return true;
    }
}
