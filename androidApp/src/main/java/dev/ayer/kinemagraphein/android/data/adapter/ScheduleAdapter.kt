package dev.ayer.kinemagraphein.android.data.adapter

import dev.ayer.kinemagraphein.android.data.dto.ScheduleModel
import dev.ayer.kinemagraphein.android.entity.time.Schedule

fun ScheduleModel.toSchedule(): Schedule {
    return Schedule(
        time = time,
        weekdays = days
    )
}
