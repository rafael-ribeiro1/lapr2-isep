package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.ListTasks;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;

/**
 * Controller Class for the Scene Register Task.
 */
public class RegisterTaskController {
    private Platform m_platform;
    private Organization org;
    private ListTasks listTask;
    private Task task;

    /**
     * Constructor class that initializes all necessary parameters
     */
    public RegisterTaskController() {
        this.m_platform=MainController.getPlatform();
        RegistryOrganizations m_RegistryOrganizations=m_platform.getRegistryOrganizations();
        this.org = m_RegistryOrganizations.getOrgByUserEmail(MainController.getLoginSystem().getLoggedUserEmail());
        listTask=org.getListTask();
    }

    /**
     *  Method that creates and returns a new Instance of Task
     * @param taskID ID of the task
     * @param taskAssignDuration Duration of the Task
     * @param taskCostPerHour Cost per hour of the Task
     * @param taskCategory Category of the Task
     * @param taskDescripton Description of the Task.
     * @return A new intansce of the Task
     */

    public Task newTask(String taskID, int taskAssignDuration, Double taskCostPerHour, String taskCategory,String taskDescripton){
        task=listTask.newTask(taskID,taskAssignDuration,taskCostPerHour,taskCategory,taskDescripton);
        return task;
    }

    /**
     * Method that register the task
     * @return
     */
    public boolean registerTask(){
           return listTask.registerTask(task);

    }



}
