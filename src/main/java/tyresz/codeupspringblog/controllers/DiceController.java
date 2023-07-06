package tyresz.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DiceController {

    private final Random random = new Random();
    private final List<Integer> recentRolls = new ArrayList<>();

    @GetMapping("/roll-dice")
    public String showRollDicePage() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        int rolledNumber = random.nextInt(6) + 1;
        recentRolls.add(rolledNumber);

        model.addAttribute("rolledNumber", rolledNumber);
        model.addAttribute("userGuess", guess);
        model.addAttribute("message", getMessage(guess, rolledNumber));
        model.addAttribute("recentRolls", recentRolls);

        return "roll-dice";
    }

    private String getMessage(int guess, int rolledNumber) {
        if (guess == rolledNumber) {
            return "Congratulations! Your guess was correct.";
        } else {
            return "Oops! Your guess was incorrect.";
        }
    }
}
