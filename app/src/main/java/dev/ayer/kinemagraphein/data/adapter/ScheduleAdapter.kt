package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.dto.ScheduleModel
import dev.ayer.kinemagraphein.entity.time.Schedule

fun ScheduleModel.toSchedule(): Schedule {
    return Schedule(
        time = time,
        weekdays = days
    )
}