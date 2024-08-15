package sharpBubbles.authService.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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

    @GetMapping("/allCompleted")
    public ResponseEntity<?> getAllCompleted(Principal principal,
                                             @RequestParam(required = false) String ownerType,
                                             @RequestParam(required = false) Long teamId) {

        Long ownerId = null;
        if (ownerType.equals("USER")) {
            ownerId = userService.getPrincipalUserId(principal);
        }
        if (ownerType.equals("TEAM")) {
            ownerId = teamId;
        }
        String response = restTemplate.getForObject(taskService + '/' + ownerId + "/allCompletedTasks" + getParams(ownerType, teamId), String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onTheDay")
    public ResponseEntity<?> getTasksOnTheDay(Principal principal,
                                              @RequestParam(required = false) String ownerType,
                                              @RequestParam(required = false) Long teamId) {

        Long ownerId = null;
        if (ownerType.equals("USER")) {
            ownerId = userService.getPrincipalUserId(principal);
        }
        if (ownerType.equals("TEAM")) {
            ownerId = teamId;
        }
        String response = restTemplate.getForObject(taskService + '/' + ownerId + "/tasksOnTheDay" + getParams(ownerType, teamId), String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onOtherDays")
    public ResponseEntity<?> getTasksOnOtherDays(Principal principal,
                                                 @RequestParam(required = false) String ownerType,
                                                 @RequestParam(required = false) Long teamId) {

        Long ownerId = null;
        if (ownerType.equals("USER")) {
            ownerId = userService.getPrincipalUserId(principal);
        }
        if (ownerType.equals("TEAM")) {
            ownerId = teamId;
        }
        String response = restTemplate.getForObject(taskService + '/' + ownerId + "/tasksOnOtherDays" + getParams(ownerType, teamId), String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<?> getTasksIncomplete(Principal principal,
                                                @RequestParam(required = false) String ownerType,
                                                @RequestParam(required = false) Long teamId) {

        Long ownerId = null;
        if (ownerType.equals("USER")) {
            ownerId = userService.getPrincipalUserId(principal);
        }
        if (ownerType.equals("TEAM")) {
            ownerId = teamId;
        }
        String response = restTemplate.getForObject(taskService + '/' + ownerId + "/tasksIncomplete" + getParams(ownerType, teamId), String.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/onSomeday")
    public ResponseEntity<?> getAllInProgressWithoutDatePlannedImplementation(Principal principal,
                                                                              @RequestParam(required = false) String ownerType,
                                                                              @RequestParam(required = false) Long teamId) {

        Long ownerId = null;
        if (ownerType.equals("USER")) {
            ownerId = userService.getPrincipalUserId(principal);
        }
        if (ownerType.equals("TEAM")) {
            ownerId = teamId;
        }
        String response = restTemplate.getForObject(taskService + '/' + ownerId + "/allInProgressTasksWithoutDatePlannedImplementation" + getParams(ownerType, teamId), String.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(Principal principal, @RequestBody Object request) {
        Long ownerId = userService.getPrincipalUserId(principal);
        String response = restTemplate.postForObject(taskService + '/' + ownerId + "/createTask", request, String.class);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/change/{taskId}")
    public ResponseEntity<?> changeTask(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long ownerId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + ownerId + "/changeTask/" + taskId, request);

        return ResponseEntity.ok("Task successfully modified!");
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(Principal principal, @PathVariable Long taskId) {
        Long ownerId = userService.getPrincipalUserId(principal);
        restTemplate.delete(taskService + '/' + ownerId + "/deleteTask/" + taskId);

        return ResponseEntity.ok("Task successfully deleted!");
    }

    @PutMapping("/changeStatusOnCompleted/{taskId}")
    public ResponseEntity<?> changeTaskStatusOnCompleted(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long ownerId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + ownerId + "/changeTaskStatusOnCompleted/" + taskId, request);

        return ResponseEntity.ok("Task status has been successfully changed to completed!");
    }

    @PutMapping("/changeStatusOnInProgress/{taskId}")
    public ResponseEntity<?> changeTaskStatusOnInProgress(Principal principal, @PathVariable Long taskId, @RequestBody Object request) {
        Long ownerId = userService.getPrincipalUserId(principal);
        restTemplate.put(taskService + '/' + ownerId + "/changeTaskStatusOnInProgress/" + taskId, request);

        return ResponseEntity.ok("Task status has been successfully changed to completed!");
    }

    private String getParams(String ownerType, Long teamId) {
        return String.format("?ownerType=%s", ownerType);
    }
}
