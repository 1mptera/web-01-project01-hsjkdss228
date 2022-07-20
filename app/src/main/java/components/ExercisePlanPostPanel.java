package components;

import application.AerobicExerciseRecords;
import models.ExercisePlanPost;
import models.ExerciseRecordPost;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExercisePlanPostPanel extends JPanel {
  public ExercisePlanPostPanel(
      List<ExercisePlanPost> exercisePlanPosts,
      ExercisePlanPost exercisePlanPost,
      List<ExerciseRecordPost> exerciseRecordPosts) {
    this.setLayout(new GridLayout(0, 1));

    JButton backButton = new JButton("뒤로가기");
    backButton.addActionListener(event -> {
      JPanel seeExercisePlanPostsPanel = new SeeExercisePlanPostsPanel(
          exercisePlanPosts, exerciseRecordPosts
      );
      AerobicExerciseRecords.mainFrame().showContentPanel(seeExercisePlanPostsPanel);
    });
    this.add(backButton);

    JLabel titleLabel = new JLabel(exercisePlanPost.title());
    this.add(titleLabel);

    JLabel dateLabel = new JLabel(exercisePlanPost.date());
    this.add(dateLabel);

    JLabel exerciseTypeLabel = new JLabel(exercisePlanPost.exerciseType());
    this.add(exerciseTypeLabel);

    JLabel exerciseTimeLabel = new JLabel(exercisePlanPost.exerciseTime());
    this.add(exerciseTimeLabel);

    JLabel stopoverPointsLabel = new JLabel(exercisePlanPost.stopoverPoints());
    this.add(stopoverPointsLabel);

    JLabel exerciseDistanceLabel = new JLabel(exercisePlanPost.exerciseDistance());
    this.add(exerciseDistanceLabel);

    JPanel descriptionPanel = new JPanel();
    descriptionPanel.add(new JLabel("상세 설명: "));
    JTextArea descriptionTextArea = new JTextArea(exercisePlanPost.description());
    descriptionTextArea.setColumns(20);
    descriptionTextArea.setRows(2);
    descriptionTextArea.setLineWrap(true);
    descriptionTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
    scrollPane.createVerticalScrollBar();
    descriptionPanel.add(scrollPane);
    this.add(descriptionPanel);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(1, 3));
    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      JPanel editExercisePlanPostPanel = new EditExercisePlanPostPanel(
          exercisePlanPosts, exercisePlanPost, exerciseRecordPosts, EditExercisePlanPostPanel.MODIFICATION
      );
      AerobicExerciseRecords.mainFrame().showContentPanel(editExercisePlanPostPanel);
    });
    buttonsPanel.add(modifyButton);
    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      for (ExercisePlanPost found : exercisePlanPosts) {
        if (found.uniqueNumber() == exercisePlanPost.uniqueNumber()) {
          found.delete();
          break;
        }
      }
      JPanel seeExercisePlanPostsPanel = new SeeExercisePlanPostsPanel(
          exercisePlanPosts, exerciseRecordPosts
      );
      AerobicExerciseRecords.mainFrame().showContentPanel(seeExercisePlanPostsPanel);
    });
    buttonsPanel.add(deleteButton);
    JButton createExerciseRecordPostButton = new JButton("결과 기록하기");
    createExerciseRecordPostButton.addActionListener(event -> {
      JPanel editExerciseRecordPostPanel = new EditExerciseRecordPostPanel(
        exercisePlanPosts, exercisePlanPost, exerciseRecordPosts, EditExerciseRecordPostPanel.CREATION
      );
      AerobicExerciseRecords.mainFrame().showContentPanel(editExerciseRecordPostPanel);
    });
    buttonsPanel.add(createExerciseRecordPostButton);
    this.add(buttonsPanel);
  }
}