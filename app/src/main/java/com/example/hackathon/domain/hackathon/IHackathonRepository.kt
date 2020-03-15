package com.example.hackathon.domain.hackathon

import com.example.hackathon.data.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.state.State

interface IHackathonRepository {

    suspend fun getHackathons(): State<Paging<Hackathon>>
}