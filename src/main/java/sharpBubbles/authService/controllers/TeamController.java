package sharpBubbles.authService.controllers;


import org.springframework.web.bind.annotation.*;
import sharpBubbles.authService.models.Team;
import sharpBubbles.authService.models.User;

import java.util.List;

@RestController
@RequestMapping("/task-tracker/team")
public class TeamController {

    @PostMapping("/createTeam/{user}")
    public void createTeam(@PathVariable User user, @RequestBody Team team) {
        team.getTeamUsers().add(user);
        user.getUsersTeam().add(team);
    }

    @GetMapping("/getUserTeams/{user}")
    public List<Team> getUserTeams(@PathVariable User user) {
        return user.getUsersTeam();
    }

    @GetMapping("/getTeamUsers/{team}")
    public List<User> getTeamUsers(@PathVariable Team team) {
        return team.getTeamUsers();
    }

}
