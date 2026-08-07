function EventLog({ events }) {

  return (
    <section className="event-log-section">

      <h2>Recent Grid Events</h2>

      {events.length === 0 ? (

        <p className="event-log-empty">
          No grid events received yet.
        </p>

      ) : (

        <div className="event-log-container">

          {events.map((event) => (

            <div
              className="event-log-item"
              key={event.id}
            >

              <div className="event-log-main">

                <strong>
                  Grid Node {event.nodeId}
                </strong>

                <span className="event-log-status">
                  {event.previousStatus}
                  {' → '}
                  {event.status}
                </span>

              </div>

              <div className="event-log-details">

                <span>
                  Consumption: {event.powerConsumption}
                </span>

                <span>
                  Generation: {event.powerGeneration}
                </span>

                <span>
                  {event.time}
                </span>

              </div>

            </div>

          ))}

        </div>

      )}

    </section>
  )
}

export default EventLog