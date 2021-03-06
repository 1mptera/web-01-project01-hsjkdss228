package components;

import models.ExercisePlanPost;
import models.ExerciseRecordPost;
import utils.FileLoader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame {
  private JPanel contentPanel = new JPanel();

  public MainFrame(
      List<ExercisePlanPost> exercisePlanPosts,
      List<ExerciseRecordPost> exerciseRecordPosts, FileLoader fileLoader) {
    this.setTitle("유산소 운동 계획 및 기록 프로그램");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          fileLoader.saveExercisePlanPosts(exercisePlanPosts);
          fileLoader.saveExerciseRecordPosts(exerciseRecordPosts);
          fileLoader.saveCurrentUniqueNumberCount();
        } catch (IOException exception) {
          throw new RuntimeException(exception);
        }
      }
    });

    JPanel mainMenuPanel = new MainMenuPanel(exercisePlanPosts, exerciseRecordPosts);
    replaceContentPanel(mainMenuPanel);
  }

  public void replaceContentPanel(JPanel panel) {
    contentPanel.removeAll();
    contentPanel = panel;
    this.add(contentPanel);
    contentPanel.setVisible(false);
    contentPanel.setVisible(true);
    this.setVisible(true);
  }
}
