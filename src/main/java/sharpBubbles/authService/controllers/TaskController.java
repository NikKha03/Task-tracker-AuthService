package sharpBubbles.authService.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sharpBubbles.authService.DTO.TaskRequest;
import sharpBubbles.authService.models.User;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/task-tracker/task")
public class TaskController {

    public TaskController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${services.task}")
    private String taskService;

    private String tgService = "";

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/allTasks", String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/allCompleted")
    public ResponseEntity<?> getAllCompleted(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/allCompletedTasks", String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onTheDay")
    public ResponseEntity<?> getTasksOnTheDay(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/tasksOnTheDay", String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onOtherDays")
    public ResponseEntity<?> getTasksOnOtherDays(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/tasksOnOtherDays", String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<?> getTasksIncomplete(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/tasksIncomplete", String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onSomeday")
    public ResponseEntity<?> getAllInProgressWithoutDatePlannedImplementation(Principal principal) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.getForObject(taskService + '/' + currentUserId + "/allInProgressTasksWithoutDatePlannedImplementation", String.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(Principal principal, @RequestBody TaskRequest request) {
        JSONObject responseTaskService = new JSONObject();
        responseTaskService.put("header", request.getHeader());
        responseTaskService.put("plannedImplDate", request.getPlannedImplDate());
        responseTaskService.put("comment", request.getComment());

        Long currentUserId = userService.getPrincipalUserId(principal);
        restTemplate.postForObject(taskService + '/' + currentUserId + "/createTask", responseTaskService, String.class);

        /*
        // Отправка данных в tg сервис
        User user = userService.findUserByEmail(principal.getName()).orElse(null);
        if (user != null) {
            String userTgName = user.getTg();
            String notifications = request.getNotifications();

            JSONObject responseTgService = new JSONObject();
            responseTgService.put("tgName", userTgName);
            responseTgService.put("notifications", notifications);

            restTemplate.postForObject(tgService, responseTgService.toString(), String.class);
        }
        */

        return ResponseEntity.ok("Task is successfully created!");
    }

    @PutMapping("/change/{taskId}")
    public ResponseEntity<?> changeTask(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + currentUserId + "/changeTask/" + taskId, request);

        return ResponseEntity.ok("Task successfully modified!");
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(Principal principal, @PathVariable Long taskId) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        restTemplate.delete(taskService + '/' + currentUserId + "/deleteTask/" + taskId);

        return ResponseEntity.ok("Task successfully deleted!");
    }

    @PutMapping("/changeStatusOnCompleted/{taskId}")
    public ResponseEntity<?> changeTaskStatusOnCompleted(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + currentUserId + "/changeTaskStatusOnCompleted/" + taskId, request);

        return ResponseEntity.ok("Task status has been successfully changed to completed!");
    }

    @PutMapping("/changeStatusOnInProgress/{taskId}")
    public ResponseEntity<?> changeTaskStatusOnInProgress(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + currentUserId + "/changeTaskStatusOnInProgress/" + taskId, request);

        return ResponseEntity.ok("Task status has been successfully changed to completed!");
    }
}