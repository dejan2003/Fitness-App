const id = params.get('id')

if (id == null || id === '')
    window.location.href = './index.html'

const breadcrumb = document.getElementById('breadcrumb')
const sid = document.getElementById('id')
const name = document.getElementById('name')
const surname = document.getElementById('surname')
const created = document.getElementById('created')
const updated = document.getElementById('updated')

fetch("http://localhost:8080/api/user/" + id)
.then((rsp) => {
  if (rsp.status === 200)
    return rsp.json()

  alert("Klijent nije pronađen");
  window.location.href = "./index.html"
})
.then(data=> {
    breadcrumb.innerText = `${data.name} ${data.surname}`
    sid.value = data.id
    name.value = data.name
    surname.value = data.surname
    created.value = formatDate(data.create)
    updated.value = formatDate(data.update)

    document.getElementById('save').addEventListener('click', () => {
        fetch(`http://localhost:8080/api/user/${data.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name.value,
                surname: surname.value,
            })
        })
            .then(rsp => {
                if (rsp.ok) {
                    window.location.href = './index.html'
                    return
                }
                alert(`Izmena klijenta nije uspela (HTTP ${rsp.status})`)
            })
    })
})
