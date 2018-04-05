package de.cisi.qa.resource;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class CisiQuestion {
	@NonNull @Id private Long id;
	@NonNull @Getter @Setter private String text;
	@NonNull @Getter @Setter  private int chapter;
	@NonNull @Getter @Setter  private int section;
}
