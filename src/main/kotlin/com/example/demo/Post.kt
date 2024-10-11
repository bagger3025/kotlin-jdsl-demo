package com.example.demo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post (
    @Column(name = "title") val title: String,
    @Column(name = "priority") val priority: Int,
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
)