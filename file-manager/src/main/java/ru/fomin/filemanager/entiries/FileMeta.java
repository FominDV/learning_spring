package ru.fomin.filemanager.entiries;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "file_meta")
@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class FileMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "hash", unique = true, nullable = false)
    private UUID hash;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "sub_type")
    private int subType;

}
