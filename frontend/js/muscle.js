const table = document.getElementById('muscle-group-table')
const template = document.getElementById('muscle-group')

fetch('http://localhost:8080/api/muscle-group')
    .then((rsp) => rsp.json())
    .then((data) => {
        data.forEach((muscleGroup) => {
            const copy = template.content.cloneNode(true);
            copy.querySelector(".id").innerText = muscleGroup.id;
            copy.querySelector(".name").innerText = muscleGroup.name;
            copy.querySelector('.created').innerText = formatDate(muscleGroup.create)
            copy.querySelector('.updated').innerText = formatDate(muscleGroup.update)
            copy.querySelector(".edit").href = `./edit-muscle-group.html?id=${muscleGroup.id}`;
            copy.querySelector('.remove').addEventListener('click', () => {
                if (confirm(`Želite obrisati grupu ${muscleGroup.name}`)) {
                    fetch(`http://localhost:8080/api/muscle-group/${muscleGroup.id}`, {
                        method: 'DELETE',
                    })
                        .then(rsp => {
                            if (rsp.status === 204) {
                                window.location.href = './muscle-group.html'
                                return
                            }
                            alert(`Brisanje grupe nije uspelo (HTTP ${rsp.status})`)
                        })
                }
            })
            table.appendChild(copy);
        });
    });