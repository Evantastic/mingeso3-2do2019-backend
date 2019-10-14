package mingeso.backend.service.choiceroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceRoom {
  private List<Integer> rooms;
  private String roomTitle;
}
