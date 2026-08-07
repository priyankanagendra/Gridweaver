import { useState } from 'react'

import { balancePower } from '../services/api'


function RegionalBalancing({ onUnauthorized }) {

  const [sourceGeneration, setSourceGeneration] =
    useState('90')

  const [sourceConsumption, setSourceConsumption] =
    useState('40')

  const [targetGeneration, setTargetGeneration] =
    useState('30')

  const [targetConsumption, setTargetConsumption] =
    useState('70')

  const [result, setResult] = useState(null)

  const [loading, setLoading] = useState(false)

  const [error, setError] = useState('')


  const handleBalancePower = async () => {

    try {

      setLoading(true)
      setError('')
      setResult(null)


      const zones = [
        {
          zoneName: 'Zone A',
          powerGeneration:
            Number(sourceGeneration),
          powerConsumption:
            Number(sourceConsumption),
        },
        {
          zoneName: 'Zone B',
          powerGeneration:
            Number(targetGeneration),
          powerConsumption:
            Number(targetConsumption),
        },
      ]


      const data = await balancePower(zones)

      console.log(
        'Regional balancing result:',
        data
      )

      setResult(data)

    } catch (error) {

      console.error(
        'Regional balancing error:',
        error
      )


      if (error.message === 'UNAUTHORIZED') {

        onUnauthorized()

        return
      }


      setError(
        'Unable to perform regional power balancing'
      )

    } finally {

      setLoading(false)
    }
  }


  return (
    <section className="balancing-section">

      <h2>Regional Power Balancing</h2>

      <p className="balancing-description">
        Transfer available surplus power from
        one grid zone to another zone that has
        a power deficit.
      </p>


      <div className="balancing-zones">

        <div className="balancing-zone-card">

          <h3>Zone A</h3>

          <label>
            Power Generation
          </label>

          <input
            type="number"
            value={sourceGeneration}
            onChange={(event) =>
              setSourceGeneration(
                event.target.value
              )
            }
          />


          <label>
            Power Consumption
          </label>

          <input
            type="number"
            value={sourceConsumption}
            onChange={(event) =>
              setSourceConsumption(
                event.target.value
              )
            }
          />

        </div>


        <div className="balancing-zone-card">

          <h3>Zone B</h3>

          <label>
            Power Generation
          </label>

          <input
            type="number"
            value={targetGeneration}
            onChange={(event) =>
              setTargetGeneration(
                event.target.value
              )
            }
          />


          <label>
            Power Consumption
          </label>

          <input
            type="number"
            value={targetConsumption}
            onChange={(event) =>
              setTargetConsumption(
                event.target.value
              )
            }
          />

        </div>

      </div>


      <button
        className="balance-power-button"
        onClick={handleBalancePower}
        disabled={loading}
      >

        {loading
          ? 'Balancing...'
          : 'Balance Power'}

      </button>


      {error && (

        <p className="balancing-error">
          {error}
        </p>

      )}


      {result && (

        <div className="balancing-result">

          <h3>Balancing Result</h3>

          <p>
            <strong>Source Zone:</strong>{' '}
            {result.sourceZone}
          </p>

          <p>
            <strong>Target Zone:</strong>{' '}
            {result.targetZone}
          </p>

          <p>
            <strong>Source Surplus:</strong>{' '}
            {result.sourceSurplus}
          </p>

          <p>
            <strong>Target Deficit:</strong>{' '}
            {result.targetDeficit}
          </p>

          <p>
            <strong>Transferred Power:</strong>{' '}
            {result.transferredPower}
          </p>

          <p>
            <strong>Status:</strong>{' '}

            <span
              className={`balancing-status ${
                result.status
                  ? result.status.toLowerCase()
                  : ''
              }`}
            >
              {result.status}
            </span>

          </p>

        </div>

      )}

    </section>
  )
}

export default RegionalBalancing