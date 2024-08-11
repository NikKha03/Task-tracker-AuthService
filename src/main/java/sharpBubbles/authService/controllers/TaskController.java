package sharpBubbles.authService.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sharpBubbles.authService.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> createTask(Principal principal, @RequestBody Object request) {
        Long currentUserId = userService.getPrincipalUserId(principal);
        String response = restTemplate.postForObject(taskService + '/' + currentUserId + "/createTask", request, String.class);

        return ResponseEntity.ok(response);
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
