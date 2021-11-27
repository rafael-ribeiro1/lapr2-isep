package isep.dei.lapr2.model;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Class that represents Task
 */
public class Task implements Serializable {
    /**
     * Task of ID
     */
    private String taskID;
    /**
     * Description of the Task
     */
    private String taskDescription;
    /**
     * Duration of the task
     */
    private int taskAssignDuration;
    /**
     * Cost per hour of the task
     */
    private Double taskCostPerHour;
    /**
     * Category of the task
     */
    private String taskCategory;
    /**
     * Boolean that represents if the task has transaction or not.
     */
    private boolean taskHasTransaction;

    /**
     * Constructor of Class
     * @param taskID ID of the task
     * @param taskAssignDuration Duration of the task
     * @param taskCostPerHour Cost per hour of the task
     * @param taskCategory Category of the task
     * @param taskDescripton Description of the task
     */
    public Task(String taskID, int taskAssignDuration, Double taskCostPerHour, String taskCategory,String taskDescripton) {
        setTaskID(taskID);
        setTaskAssignDuration(taskAssignDuration);
        setTaskCostPerHour(taskCostPerHour);
        setTaskCategory(taskCategory);
        setTaskDescription(taskDescripton);
        this.taskHasTransaction=false;
    }

    /**
     * Method that validates and set the value of the task ID.
     * @param taskID ID of the Task
     */
    public void setTaskID(String taskID) {
        if(taskID.trim().isEmpty()||taskID==null)throw new IllegalArgumentException("Invalid Task ID !");
        this.taskID = taskID;
    }

    /**
     * Setter that validates and defines the Description of the task.
     * @param taskDescription Description of the task
     */
    public void setTaskDescription(String taskDescription) {
        if(taskDescription.trim().isEmpty()||taskDescription==null)throw new IllegalArgumentException("Invalid Task Description ! ");
        this.taskDescription = taskDescription;
    }

    /**
     * Setter that validates and defines the Duration of the Task.
     * @param taskAssignDuration Duration of the task
     */
    public void setTaskAssignDuration(int taskAssignDuration) {
        if(taskAssignDuration<=0)throw new IllegalArgumentException("Invalid Duration for Assign !");
        this.taskAssignDuration = taskAssignDuration;
    }

    /**
     * Setter that validate and defines the value of cost per hour of the task
     * @param taskCostPerHour Cost per hour of the task.
     */
    public void setTaskCostPerHour(Double taskCostPerHour) {
        if(taskCostPerHour<=0)throw new IllegalArgumentException("Invalid Task Cost per hour !");
        this.taskCostPerHour = taskCostPerHour;
    }

    /**
     * Setter that validate and defines the value of the cattegory of the task.
     * @param taskCategory Category of the task
     */
    public void setTaskCategory(String taskCategory) {
        if(taskCategory.trim().isEmpty()||taskCategory==null)throw new IllegalArgumentException("Invalid Task Category !");
        this.taskCategory = taskCategory;
    }

    /**
     * getter of Task has Transaction.
     * @return Value of the task has Transaction.
     */
    public boolean getTaskHasTransaction(){
        return taskHasTransaction;
    }

    /**
     * Getter of the Duration of the Task.
     * @return Task Duration.
     */
    public int getTaskAssignDuration() {
        return taskAssignDuration;
    }

    /**
     * Getter of the Cost per hour of the Task.
     * @return Cost per hour of the Task
     */
    public Double getTaskCostPerHour() {
        return taskCostPerHour;
    }

    /**
     * Setter that defines that the task has transaction.
     */
    public void setTaskHasTransactionTrue(){
        this.taskHasTransaction=true;
    }

    /**
     * Getter that returns the ID of the task
     * @return Task ID
     */
    public String getTaskID() {
        return this.taskID;
    }

    /**
     * Getter that returns the Description of the Task.
     * @return Description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Method that return information about the task
     * @return Information about the task.
     */

    @Override
    public String toString() {
        return String.format("ID: %S \nCategory: %s  \nDescription of the task : %s",taskID,taskCategory,taskDescription);
    }
}
