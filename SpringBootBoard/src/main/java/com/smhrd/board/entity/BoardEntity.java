package com.smhrd.board.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoardEntity {
	// entity에는 pk가 존재해야 함!

	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increase
	private Long id; // 번호
	
	// 각 컬럼들에게 not null(imgPath 빼고)
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer; 
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content; // 내용의 경우 길이가 길어야 하므로 
	
	private String imgPath; 
	// img(file)를 넣는 것이 아니라 경로를 DB에 저장
	// DB 서버에 직접적으로 이미지와 같은 파일을 저장하지 않음(무거워지고 느려지기 때문)
	// 이미지는 서버에 저장하고 해당 서버의 주소를 DB에 저장
	
	// DB에 저장 시 insert는 가능하나 update는 불가능
	@Column(nullable = false, updatable = false) 
	private LocalDate writeDay; 
	
	// 글 작성 시 자동으로 writeDay가 입력되도록 코드 작성
	// entity가 생성될 때 실행하는 코드
	@PrePersist
	protected void onCreate() {
		this.writeDay = LocalDate.now();
	}
	
}
