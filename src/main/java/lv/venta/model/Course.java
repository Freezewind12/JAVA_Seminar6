package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "CourseTable")
@Entity
public class Course {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IDc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idc;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z _]+")
	@Size(min = 3, max = 30)
	@Column(name = "Title")
	private String title;
	
	@NotNull
	@Max(20)
	@Column(name = "CP")
	private int cp;
	
	@OneToOne
	@JoinColumn(name = "Idp") //need to specify column name
	private Professor professor;
	
	@OneToMany(mappedBy = "course")
	@ToString.Exclude
	private Collection<Grade> grades;
	
	public Course(String title, int creditpoints, Professor professor)
	{
		setTitle(title);
		setCp(creditpoints);
		setProfessor(professor);
	}
}
