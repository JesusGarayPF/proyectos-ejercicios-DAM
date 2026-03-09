interface AudioPlayer{
    audioVolume: number;
    songDuration: number;
    songTitle: string;
    details: Details;
}

interface Details{
    author: string;
    year: number;
}

const audioPlayer: AudioPlayer = {
    audioVolume: 90,
    songDuration: 36,
    songTitle: 'Mess',
    details: {
        author: 'Unknown',
        year: 2020
    },
};

console.log('Song: ', audioPlayer.songTitle);
/*const song = 'New Song';
const {songTitle:title, songDuration:duration, details } = audioPlayer;
const {author} = details;*/

const [p1, p2, trunks = 'Not found']:string[] = ['Goku', 'Vegeta'];
console.log('Personaje 3: ', trunks);
export {}